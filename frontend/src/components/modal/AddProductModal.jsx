import React from 'react';
import PropTypes from 'prop-types';
import { useForm, FormProvider } from 'react-hook-form';

import { Stack, Dialog, DialogTitle, DialogContent } from "@mui/material";

import CustomTextField from '../CustomTextField';

export default function AddProductModal({ open, onClose }) {
    const methods = useForm({
        defaultValues: {
            name: '',
            description: '',
            price: '',
            conditions: '',
            quantity: '',
        }
    });
    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
            <DialogTitle>Describe your item</DialogTitle>
            <DialogContent>
                <FormProvider {...methods}>
                    <form>
                        <Stack direction="column">
                            <CustomTextField name="name" label="Name" />
                            <CustomTextField name="description" label="Description" />
                            <CustomTextField name="price" label="Price" />
                            <CustomTextField name="conditions" label="Conditions" />
                            <CustomTextField name="quantity" label="Quantity" />
                        </Stack>
                    </form>
                </FormProvider>
            </DialogContent>
        </Dialog>
    )
}

AddProductModal.propTypes = {
    open: PropTypes.bool.isRequired,
    onClose: PropTypes.func.isRequired,
}