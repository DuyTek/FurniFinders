import { Link } from 'react-router-dom';
import React, { useState } from 'react';

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
import Iconify from '../../components/iconify';
import { useRouter } from '../../routes/hooks';
import { LOGIN } from '../../constants/router-link';

// ----------------------------------------------------------------------

export default function SignUpView() {
    const theme = useTheme();

    const router = useRouter();

    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);


    const handleClick = () => {
        router.push('/dashboard');
    };

    const renderForm = (
        <>
            <Stack spacing={3}>
                <Stack spacing={3} direction="row">
                    <TextField name="firstname" label="First name" />
                    <TextField name="lastname" label="Last name" />
                </Stack>
                <TextField name="email" label="Email address" />
                <TextField name="phone" label="Phone number" />

                <TextField
                    name="password"
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
                />

                <TextField
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
                onClick={handleClick}
            >
                Login
            </LoadingButton>
        </>
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
                    <Typography variant="h4">Sign up to Furni</Typography>

                    <Typography variant="body2" sx={{ mt: 2, mb: 5 }}>
                        Already have an account?
                        <Link to={LOGIN} variant="subtitle2" sx={{ ml: 0.5 }}>
                            Login here
                        </Link>
                    </Typography>

                    {renderForm}
                </Card>
            </Stack>
        </Box>
    );
}
