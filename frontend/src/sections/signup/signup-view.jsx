/* eslint-disable import/no-extraneous-dependencies */
import * as yup from 'yup';
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
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
import { List, Tooltip, ListItem } from '@mui/material';
import InputAdornment from '@mui/material/InputAdornment';

import Logo from '../../components/logo';
import { bgGradient } from '../../theme/css';
import { signUp } from '../../service/authen';
import Iconify from '../../components/iconify';
import { LOGIN } from '../../constants/router-link';
import PASSWORD_CRITERIA from '../../constants/constants';
import { authEnd, authStart } from '../../reducer/authSlice';
import { requiredField, validateEmail, validatePassword } from '../../utils/validation';

// ----------------------------------------------------------------------
export default function SignUpView() {
    const theme = useTheme();
    const navigateTo = useNavigate();
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);
    const dispatch = useDispatch();

    const schema = yup.object({
        user_first_name: requiredField('First name'),
        user_last_name: requiredField('Last name'),
        user_email: validateEmail(),
        user_phone: yup.number().typeError('Phone must contain numbers only').required('Phone is required'),
        user_password: validatePassword(),
        confirmPassword: yup.string().oneOf([yup.ref('user_password'), null], 'Passwords must match').required('Confirm password is required'),
    });
    const methods = useForm({
        defaultValues: {
            user_first_name: '',
            user_last_name: '',
            user_email: '',
            user_phone: '',
            user_password: '',
            confirmPassword: '',
        },
        mode: 'all',
        resolver: yupResolver(schema),
    });

    const { handleSubmit, control } = methods;

    const onSubmit = (data) => {
        dispatch(authStart());
        signUp(data).then((response) => {
            if (response.status === 200) {
                navigateTo(LOGIN);
            }
        }).catch((error) => {
            throw new Error(error);
        }).finally(() => dispatch(authEnd()));
    };

    const renderForm = (
        <FormProvider {...methods}>
            <form onSubmit={handleSubmit(onSubmit)}>
                <Stack spacing={2}>
                    <Stack spacing={2} direction="row">
                        <Controller
                            control={control}
                            name="user_first_name"
                            render={({ fieldState: { error }, field }) => <TextField {...field} label="First name" helperText={error?.message} error={Boolean(error?.message)} />}
                        />
                        <Controller
                            control={control}
                            name="user_last_name"
                            render={({ fieldState: { error }, field }) => <TextField {...field} label="Last name" helperText={error?.message} error={Boolean(error?.message)} />}
                        />
                    </Stack>
                    <Controller
                        control={control}
                        name="user_email"
                        render={({ fieldState: { error }, field }) => <TextField {...field} label="Email" helperText={error?.message} error={Boolean(error?.message)} />}
                    />
                    <Controller
                        control={control}
                        name="user_phone"
                        render={({ fieldState: { error }, field }) => <TextField {...field} label="Phone" helperText={error?.message} error={Boolean(error?.message)} />}
                    />
                    <Controller
                        control={control}
                        name="user_password"
                        render={({ fieldState: { error }, field }) =>
                            <Tooltip placement='right-start' title={<List sx={{ listStyleType: 'disc', fontSize: 13 }}>
                                Your password must include:
                                {PASSWORD_CRITERIA.map((item) => (
                                    <ListItem key={item} disablePadding sx={{ display: 'list-item', marginLeft: '14px' }}>{item}</ListItem>
                                ))}
                            </List>}>
                                <TextField
                                    {...field}
                                    label="Password"
                                    type={showPassword ? 'text' : 'password'}
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
                                />
                            </Tooltip>}
                    />

                    <Controller
                        control={control}
                        name="confirmPassword"
                        render={({ fieldState: { error }, field }) =>
                            <TextField
                                {...field}
                                name="confirmPassword"
                                label="Confirm password"
                                type={showConfirmPassword ? 'text' : 'password'}
                                InputProps={{
                                    endAdornment: (
                                        <InputAdornment position="end">
                                            <IconButton onClick={() => setShowConfirmPassword(!showConfirmPassword)} edge="end">
                                                <Iconify icon={showConfirmPassword ? 'eva:eye-fill' : 'eva:eye-off-fill'} />
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
                        minWidth: 500,
                        maxWidth: 460,
                    }}
                >
                    <Typography variant="h4">Sign up to Wood</Typography>

                    <Typography variant="body2" sx={{ mt: 2, mb: 5 }}>
                        Already have an account?
                        <Link to={LOGIN} variant="subtitle2" sx={{ ml: 0.5 }}>
                            {' '}
                            Login here
                        </Link>
                    </Typography>

                    {renderForm}
                </Card>
            </Stack>
        </Box>
    );
}
