import React from 'react';
import PropTypes from 'prop-types';
import { enqueueSnackbar } from 'notistack';
import { useForm, Controller, FormProvider } from 'react-hook-form';

import { AttachMoney } from '@mui/icons-material';
import { Grid, Dialog, Button, Slider, Typography, DialogTitle, DialogContent, DialogActions } from "@mui/material";

import CustomTextField from '../CustomTextField';
import { postProduct } from '../../service/product';

export const sliderMarks = [
    {
        value: 0,
        label: 'Vintage',
        color: 'warning'
    },
    {
        value: 25,
        label: 'Old',
        color: 'warning'
    },
    {
        value: 50,
        label: 'Used',
        color: 'info'
    },
    {
        value: 75,
        label: 'Good',
        color: 'primary'
    },
    {
        value: 100,
        label: 'New',
        color: 'success'
    }
]

export default function AddProductModal({ open, onClose }) {
    const methods = useForm({
        defaultValues: {
            product_name: '',
            product_description: '',
            product_price: '',
            product_percentage: 50,
            product_quantity: '',
        }
    });

    const { handleSubmit, control } = methods;


    const onSubmit = (data) => {
        postProduct(data).then((response) => {
            onClose();
            enqueueSnackbar('Product added successfully. Please wait for admin approval', { variant: 'success' });
        });
    }
    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
            <DialogTitle>What are you selling?</DialogTitle>
            <DialogContent>
                <FormProvider {...methods}>
                    <form onSubmit={handleSubmit(onSubmit)} id='add-product-form'>
                        <Grid container display="flex" direction="column">
                            <Grid sx={{
                                border: '1px dashed grey',
                                padding: 10,
                                textAlign: 'center',
                            }}>
                                <Typography>Upload image</Typography>
                            </Grid>
                            <CustomTextField name="product_name" label="Name" fullWidth />
                            <CustomTextField name="product_description" label="Description" fullWidth multiline rows={4} />
                            <Grid container spacing={3}>
                                <Grid item>
                                    <CustomTextField
                                        name="product_price"
                                        label="Price"
                                        fullWidth
                                        type="number"
                                        startAdornment={<AttachMoney />}
                                    />
                                </Grid>
                                <Grid item>
                                    <CustomTextField name="product_quantity" label="Quantity" fullWidth type="number" />
                                </Grid>
                            </Grid>
                            <Typography id='input-slider'>
                                Conditions
                            </Typography>
                            <Grid item pl={3}>
                                <Controller
                                    control={control}
                                    name='product_percentage'
                                    render={({ field }) => (
                                        <Slider
                                            {...field}
                                            size='sm'
                                            min={0}
                                            max={100}
                                            marks={sliderMarks}
                                            step={25}
                                            aria-labelledby='input-slider'
                                            sx={{
                                                color: 'gray'
                                            }}
                                        />
                                    )}
                                />
                            </Grid>
                        </Grid>
                    </form>
                </FormProvider>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button variant='contained' type='submit' form='add-product-form'>Post</Button>
            </DialogActions>
        </Dialog >
    )
}

AddProductModal.propTypes = {
    open: PropTypes.bool.isRequired,
    onClose: PropTypes.func.isRequired,
}