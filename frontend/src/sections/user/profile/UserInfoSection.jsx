import * as yup from 'yup'
import dayjs from 'dayjs';
import React from 'react';
import 'dayjs/locale/en-gb'
import PropTypes from 'prop-types';
import { useSnackbar } from 'notistack';
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm, Controller, FormProvider } from "react-hook-form";

import { LoadingButton } from '@mui/lab';
import Typography from '@mui/material/Typography';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import {
    Grid, alpha, Radio, Stack, Avatar, FormLabel,
    IconButton, RadioGroup, FormControl, FormControlLabel
} from '@mui/material';

import { account } from "../../../_mock/account";
import { updateProfile } from "../../../service/user";
import CustomTextField from "../../../components/CustomTextField";
import { requiredField, validateEmail } from '../../../utils/validation';

export default function UserInfoSection({ user }) {
    const { enqueueSnackbar } = useSnackbar();
    const profileSchema = yup.object({
        user_first_name: requiredField('First name'),
        user_last_name: requiredField('Last name'),
        user_email: validateEmail(),
        user_phone: yup.number().typeError('Phone must contain numbers only').required('Phone is required'),
        user_address: yup.string().nullable(),
        user_dob: yup.date().nullable(),
        user_gender: yup.string().nullable(),
    });

    const methods = useForm({
        defaultValues: {
            user_first_name: user.user_first_name,
            user_last_name: user.user_last_name,
            user_email: user.user_email,
            user_phone: `0${user.user_phone}`,
            user_gender: user.user_gender,
            user_address: user.user_address,
            user_dob: user.user_dob,
        },
        mode: 'all',
        resolver: yupResolver(profileSchema),
    });
    const { handleSubmit, control } = methods;
    const onSubmit = (data) => {
        updateProfile(user.user_id, data).then((response) => {
            enqueueSnackbar(response.data, { variant: 'success' });
        }).catch((error) => {
            enqueueSnackbar(error.response.data, { variant: 'error' });
        });
    }

    return (
        <FormProvider {...methods} id="user-info">
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
                <Stack>

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
                    <Controller control={control} name='user_gender' render={(({ field, fieldState: { error } }) => (
                        <FormControl>
                            <FormLabel id="demo-controlled-radio-buttons-group" sx={{ color: 'black', fontSize: 12 }}>Gender</FormLabel>
                            <RadioGroup
                                aria-labelledby="demo-controlled-radio-buttons-group"
                                value={field.value}
                                row
                                {...field}
                            >
                                <FormControlLabel value='FEMALE' control={<Radio />} label="Female" />
                                <FormControlLabel value='MALE' control={<Radio />} label="Male" />
                                <FormControlLabel value='OTHERS' control={<Radio />} label="Others" />
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
                </Stack>
            </form>
        </FormProvider>
    )
}

UserInfoSection.propTypes = {
    user: PropTypes.object.isRequired,
}