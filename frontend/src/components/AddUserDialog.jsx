import * as yup from 'yup';
import PropTypes from "prop-types";
import React, { useState } from 'react';
import { enqueueSnackbar } from 'notistack';
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm, Controller, FormProvider } from "react-hook-form";

import { LoadingButton } from '@mui/lab';
import { List, Stack, Dialog, Tooltip, ListItem, TextField, IconButton, DialogTitle, DialogContent, InputAdornment } from "@mui/material";

import Iconify from "./iconify/iconify";
import { signUp } from '../service/authen';
import CustomTextField from './CustomTextField';
import { PASSWORD_CRITERIA } from '../constants/constants';
import { requiredField, validateEmail, validatePassword } from '../utils/validation';

export default function AddUserDialog({ open, handleClose }) {
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    const schema = yup.object({
        user_first_name: requiredField('First name'),
        user_last_name: requiredField('Last name'),
        user_email: validateEmail(),
        user_phone: yup.number().typeError('Phone must contain numbers only').required('Phone is required').max(1000000000, 'Please enter a Vietnamese phone number').min(100000000, 'Please enter a Vietnamese phone number'),
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
        signUp(data).then((response) => {
            if (response.status === 200) {
                enqueueSnackbar('Add user successfully', { variant: 'success' });
                handleClose();
            }
        }).catch((error) => {
            throw new Error(error);
        });
    };

    const renderForm = (
        <FormProvider {...methods}>
            <form onSubmit={handleSubmit(onSubmit)}>
                <Stack spacing={2} pt={3}>
                    <Stack spacing={2} direction="row">
                        <CustomTextField name="user_first_name" label="First name" />
                        <CustomTextField name="user_last_name" label="Last name" />
                    </Stack>
                    <CustomTextField name="user_email" label="Email" />
                    <CustomTextField name="user_phone" label="Phone" />
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

                <Stack direction='row' justifyContent='flex-end'>
                    <LoadingButton
                        size="small"
                        variant="outlined"
                        color="info"
                        sx={{ mt: 3, mr: 1 }}
                        onClick={handleClose}
                    >
                        Cancel
                    </LoadingButton>
                    <LoadingButton
                        size="small"
                        type="submit"
                        variant="contained"
                        color="inherit"
                        sx={{ mt: 3 }}
                    >
                        Add
                    </LoadingButton>
                </Stack>

            </form>
        </FormProvider>
    );
    return (
        <FormProvider>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle pb={5}>Add new user</DialogTitle>
                <DialogContent>
                    {renderForm}
                </DialogContent>
            </Dialog>
        </FormProvider>
    )
}

AddUserDialog.propTypes = {
    open: PropTypes.bool,
    handleClose: PropTypes.func,
};