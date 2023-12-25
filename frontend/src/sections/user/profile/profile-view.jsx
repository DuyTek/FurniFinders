import * as yup from 'yup'
import React from "react";
import { useSnackbar } from 'notistack';
import { yupResolver } from '@hookform/resolvers/yup';
import { useSelector, useDispatch } from "react-redux";
import { useForm, FormProvider } from "react-hook-form";

import LoadingButton from '@mui/lab/LoadingButton';
import { Card, Grid, Stack, alpha, Avatar, Container, Typography, IconButton } from "@mui/material";

import { account } from "../../../_mock/account";
import { update } from '../../../reducer/authSlice';
import { updateProfile } from "../../../service/user";
import CustomTextField from "../../../components/CustomTextField";
import { requiredField, validateEmail } from '../../../utils/validation';

export default function ProfileView() {
    const auth = useSelector((state) => state.auth);
    const dispatch = useDispatch();
    const { enqueueSnackbar } = useSnackbar();

    const profileSchema = yup.object({
        user_first_name: requiredField('First name'),
        user_last_name: requiredField('Last name'),
        user_email: validateEmail(),
        user_phone: yup.number().typeError('Phone must contain numbers only').required('Phone is required'),
    });

    const methods = useForm({
        defaultValues: {
            user_first_name: auth.user_first_name,
            user_last_name: auth.user_last_name,
            user_email: auth.user_email,
            user_phone: auth.user_phone,
        },
        mode: 'all',
        resolver: yupResolver(profileSchema),
    });
    const { handleSubmit } = methods;
    const onSubmit = (data) => {
        updateProfile(auth.user_id, data).then((response) => {
            dispatch(update(data))
            enqueueSnackbar(response.data, { variant: 'success' });
        }).catch((error) => {
            enqueueSnackbar(error.response.data, { variant: 'error' });
        });
    }

    const renderForm = () => (
        <FormProvider {...methods}>
            <Grid container display='flex' direction='row'>
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
                    <Grid item>
                        <CustomTextField name="user_first_name" label="First name" />
                        <CustomTextField name="user_last_name" label="Last name" />
                        <CustomTextField name="user_email" label="Email" disabled />
                        <CustomTextField name="user_phone" label="Phone" />
                    </Grid>
                    <Grid item>
                        <CustomTextField name="user_address" label="Address" />
                        <CustomTextField name="user_gender" label="Gender" />
                        <CustomTextField name="user_dob" label="Date of birth" />
                    </Grid>


                    <Grid item direction="row">
                        <LoadingButton
                            size="small"
                            type="submit"
                            variant="outlined"
                            color="info"
                        >
                            Reset
                        </LoadingButton>
                        <LoadingButton
                            size="small"
                            type="submit"
                            variant="contained"
                            color="primary"
                        >
                            Save
                        </LoadingButton>
                    </Grid>

                </form>
            </Grid>
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