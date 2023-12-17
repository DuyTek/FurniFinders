import * as yup from 'yup'
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { enqueueSnackbar } from 'notistack';
import { Link, useNavigate } from 'react-router-dom';
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm, Controller, FormProvider } from 'react-hook-form';

import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import LoadingButton from '@mui/lab/LoadingButton';
import { alpha, useTheme } from '@mui/material/styles';
import InputAdornment from '@mui/material/InputAdornment';

import Logo from '../../components/logo';
import { bgGradient } from '../../theme/css';
import { signIn } from '../../service/authen';
import Iconify from '../../components/iconify';
import { validateEmail } from '../../utils/validation';
import ResponseCode from '../../constants/responseCode';
import { USER_PRODUCTS } from '../../constants/router-link';
import { authEnd, authStart, authSuccess } from '../../reducer/authSlice';

// ----------------------------------------------------------------------

export default function LoginView() {
  const navigateTo = useNavigate();
  const theme = useTheme();
  const [showPassword, setShowPassword] = useState(false);
  const dispatch = useDispatch();

  const loginSchema = yup.object({
    user_email: validateEmail(),
    user_password: yup.string().required('Password is required'),
  })

  const methods = useForm({
    defaultValues: {
      user_email: '',
      user_password: '',
    },
    mode: 'onSubmit',
    resolver: yupResolver(loginSchema),
  });

  const { handleSubmit, control } = methods;

  const onSubmit = (params) => {
    dispatch(authStart())
    signIn(params).then((response) => {
      if (response.status === 200) {
        dispatch(authSuccess(response.data));
        navigateTo(USER_PRODUCTS);
        enqueueSnackbar('Login successfully', { variant: 'success' });
      }
    }).catch((error) => {
      if (error.code === ResponseCode.BAD_REQUEST) {
        enqueueSnackbar('Email or password is incorrect', { variant: 'error' });
      }
      throw new Error(error);
    }).finally(() => dispatch(authEnd()));
  }

  const renderForm = (

    <FormProvider {...methods}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <Stack spacing={3}>
          <Controller
            control={control}
            name="user_email"
            render={({ fieldState: { error }, field }) =>
              <TextField
                {...field}
                label="Email"
                helperText={error?.message}
                error={Boolean(error?.message)}
                autoComplete='new-email'
              />}
          />
          <Controller
            control={control}
            name="user_password"
            render={({ fieldState: { error }, field }) =>
              <TextField
                {...field}
                label="Password"
                type={showPassword ? 'text' : 'password'}
                autoComplete='new-password'
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton onClick={() => setShowPassword(!showPassword)} edge="end">
                        <Iconify icon={showPassword ? 'eva:eye-fill' : 'eva:eye-off-fill'} />
                      </IconButton>
                    </InputAdornment>
                  ),
                }}
                error={Boolean(error?.message)}
                helperText={error?.message}
              />}
          />
        </Stack>

        <Stack direction="row" alignItems="center" justifyContent="flex-end" sx={{ my: 3 }}>
          <Link variant="subtitle2" underline="hover">
            Forgot password?
          </Link>
        </Stack>

        <LoadingButton
          fullWidth
          size="large"
          type="submit"
          variant="contained"
          color="inherit"
        >
          Login
        </LoadingButton>
      </form>
    </FormProvider>
  );

  return (
    <Box
      sx={{
        ...bgGradient({
          color: alpha(theme.palette.background.default, 0.9),
          imgUrl: '/assets/background/overlay_4.jpg',
        }),
        height: 1,
      }}
    >
      <Logo
        sx={{
          position: 'fixed',
          top: { xs: 16, md: 24 },
          left: { xs: 16, md: 24 },
        }}
      />

      <Stack alignItems="center" justifyContent="center" sx={{ height: 1 }}>
        <Card
          sx={{
            p: 5,
            width: 1,
            maxWidth: 420,
          }}
        >
          <Typography variant="h4">Sign in</Typography>

          <Typography variant="body2" sx={{ mt: 2, mb: 5 }}>
            Don’t have an account?
            <Link to="/signup" variant="subtitle2" sx={{ ml: 0.5 }}>
              Get started
            </Link>
          </Typography>

          {renderForm}
        </Card>
      </Stack>
    </Box>
  );
}
