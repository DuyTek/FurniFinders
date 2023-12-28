import * as yup from 'yup'
import dayjs from 'dayjs';
import React from "react";
import 'dayjs/locale/en-gb'
import { useSnackbar } from 'notistack';
import { yupResolver } from '@hookform/resolvers/yup';
import { useSelector, useDispatch } from "react-redux";
import { useForm, Controller, FormProvider } from "react-hook-form";

import LoadingButton from '@mui/lab/LoadingButton';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import {
    Card, Grid, alpha, Radio, Avatar, Container, FormLabel,
    Typography, IconButton, RadioGroup, FormControl, FormControlLabel
} from "@mui/material";

import { account } from "../../../_mock/account";
import { update } from '../../../reducer/authSlice';
import { updateProfile } from "../../../service/user";
import { USER_GENDERS } from '../../../constants/constants';
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
        user_address: yup.string().nullable(),
        user_dob: yup.date().nullable(),
        user_gender: yup.number().nullable(),
    });

    const methods = useForm({
        defaultValues: {
            user_first_name: auth.user_first_name,
            user_last_name: auth.user_last_name,
            user_email: auth.user_email,
            user_phone: auth.user_phone,
            user_gender: auth.user_gender,
            user_address: auth.user_address,
            user_dob: auth.user_dob,
        },
        mode: 'all',
        resolver: yupResolver(profileSchema),
    });
    const { handleSubmit, control } = methods;
    const onSubmit = (data) => {
        updateProfile(auth.user_id, data).then((response) => {
            dispatch(update(data))
            enqueueSnackbar(response.data, { variant: 'success' });
        }).catch((error) => {
            enqueueSnackbar(error.response.data, { variant: 'error' });
        });
    }

    const renderForm = () => (
        <Grid container>
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
                    <Grid item container direction='row' columns={20}>
                        <Grid item xs={3} mt={1} container direction='column' alignItems='center' justifyContent='center'>
                            <IconButton
                                sx={{
                                    width: 120,
                                    height: 120,
                                    background: (theme) => alpha(theme.palette.grey[500], 0.08),
                                    mb: 3,
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
                            <CustomTextField name="user_first_name" label="First name" />
                            <CustomTextField name="user_last_name" label="Last name" />
                            <CustomTextField name="user_email" label="Email" disabled />
                            <CustomTextField name="user_phone" label="Phone" />
                        </Grid>
                        <Grid item xs={6} container direction='column' mt={21}>
                            <Controller control={control} name='user_gender' render={(({ field, fieldState: { error } }) => (
                                <FormControl>
                                    <FormLabel id="demo-controlled-radio-buttons-group" sx={{ color: 'black', fontSize: 12 }}>Gender</FormLabel>
                                    <RadioGroup
                                        aria-labelledby="demo-controlled-radio-buttons-group"
                                        name="user_gender"
                                        value={USER_GENDERS.findIndex((item) => item === auth.user_gender)}
                                        row
                                        {...field}
                                    >
                                        <FormControlLabel value={1} control={<Radio />} label="Female" />
                                        <FormControlLabel value={0} control={<Radio />} label="Male" />
                                        <FormControlLabel value={2} control={<Radio />} label="Others" />
                                    </RadioGroup>
                                    <Typography variant="caption" color="red">{error?.message}</Typography>
                                </FormControl>
                            ))}
                            />
                            <Controller
                                name='user_dob'
                                control={control}
                                render={({ field }) => (
                                    <FormControl>
                                        <LocalizationProvider dateAdapter={AdapterDayjs} adapterLocale='en-gb'>
                                            <DatePicker
                                                format='DD/MM/YYYY'
                                                disableFuture
                                                minDate={dayjs().subtract(60, 'year')}
                                                maxDate={dayjs().subtract(0, 'year')}
                                                sx={{
                                                    mt: 3,
                                                    mb: 1.4,
                                                }}
                                                label="Date of birth"
                                                {...field}
                                                value={field.value ? dayjs(field.value) : null}
                                                onChange={field.onChange}
                                            />
                                        </LocalizationProvider>
                                    </FormControl>
                                )}
                            />
                            <CustomTextField name="user_address" label="Address" />
                            <Grid item container direction='row' justifyContent='flex-end' mt={4}>
                                <LoadingButton
                                    size="small"
                                    type="reset"
                                    variant="outlined"
                                    color="info"
                                    sx={{ mr: 1 }}
                                    onClick={() => methods.reset()}
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
                        </Grid>
                    </Grid>


                </form>
            </FormProvider>
        </Grid >

    )

    return (
        <Container>
            <Typography variant="h4">
                Profile
            </Typography>
            <Card sx={{
                mt: 3,
                p: 2,
                width: '60%',
                maxHeight: '100%',
                minWidth: 1100,
                justifyContent: 'space-evenly',
            }}>
                {renderForm()}
            </Card>
        </Container>
    )
}