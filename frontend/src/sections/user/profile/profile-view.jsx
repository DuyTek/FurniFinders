import React from "react";
import * as yup from 'yup'
import { useSelector } from "react-redux";
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm, Controller, FormProvider } from "react-hook-form";

import LoadingButton from '@mui/lab/LoadingButton';
import { Card, Stack, alpha, Avatar, styled, Container, TextField, Typography, IconButton } from "@mui/material";

import { account } from "../../../_mock/account";
import { requiredField, validateEmail } from '../../../utils/validation';

const StyledTextField = styled(TextField)(({ theme }) => ({
    marginTop: theme.spacing(2),
    marginBottom: theme.spacing(2),
}))

export default function ProfileView() {
    const { user_first_name, user_last_name, user_email, user_phone } = useSelector((state) => state.auth);
    const profileSchema = yup.object({
        user_first_name: requiredField('First name'),
        user_last_name: requiredField('Last name'),
        user_email: validateEmail(),
        user_phone: yup.number().typeError('Phone must contain numbers only').required('Phone is required'),
    });
    const methods = useForm({
        defaultValues: {
            user_first_name: '',
            user_last_name: '',
            user_email: '',
            user_phone: '',
        },
        mode: 'all',
        resolver: yupResolver(profileSchema),
    });
    const { handleSubmit, control } = useForm();
    const onSubmit = (data) => {
        console.log(data);
    }
    const renderForm = () => (
        <FormProvider {...methods}>
            <form
                style={{
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    width: '100%',
                    height: '100%',
                }}
                onSubmit={handleSubmit(onSubmit)}
            >
                <IconButton
                    sx={{
                        width: 120,
                        height: 120,
                        background: (theme) => alpha(theme.palette.grey[500], 0.08),
                    }}
                >
                    <Avatar
                        src={account.photoURL}
                        alt={account.displayName}
                        sx={{
                            width: 106,
                            height: 106,
                            border: (theme) => `solid 2px ${theme.palette.background.default}`,
                        }}
                    />
                </IconButton>
                <Controller
                    control={control}
                    name="user_first_name"
                    render={({ fieldState: { error }, field }) => <StyledTextField {...field} defaultValue={user_first_name} label="First name" helperText={error?.message} error={Boolean(error?.message)} />}
                />
                <Controller
                    control={control}
                    name="user_last_name"
                    render={({ fieldState: { error }, field }) => <StyledTextField {...field} defaultValue={user_last_name} label="Last name" helperText={error?.message} error={Boolean(error?.message)} />}
                />
                <Controller
                    control={control}
                    name="user_email"
                    render={({ fieldState: { error }, field }) => <StyledTextField {...field} defaultValue={user_email} label="Email" helperText={error?.message} error={Boolean(error?.message)} />}
                />
                <Controller
                    control={control}
                    name="user_phone"
                    render={({ fieldState: { error }, field }) => <StyledTextField {...field} defaultValue={user_phone} label="Phone" helperText={error?.message} error={Boolean(error?.message)} />}
                />

                <LoadingButton
                    size="large"
                    type="submit"
                    variant="contained"
                    color="primary"
                >
                    Save changes
                </LoadingButton>
            </form>
        </FormProvider>
    )

    return (
        <Container>
            <Typography variant="h4">
                Profile
            </Typography>
            <Stack direction='row' minWidth='1000px' maxHeight='600px'>
                <Card sx={{
                    p: 2,
                    width: 1,
                    maxHeight: '100%',
                    maxWidth: '30%',
                    justifyItems: 'center',
                }}>
                    {renderForm()}
                </Card>
            </Stack>
        </Container>
    )
}