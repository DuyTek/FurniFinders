/* eslint-disable import/no-extraneous-dependencies */
import * as yup from 'yup';

const validateEmail = () => yup.string().required('Email is required').email('Email is invalid');

const validatePassword = () => yup.string().required('Password is required')
    .min(8, 'Password must be at least 8 characters')
    .matches('(?=.*[0-9])', 'Password must contain a number')
    .matches('(?=.*[A-Z])', 'Password must contain an uppercase letter')
    .matches(/(?=.*[!@#$%^&*])/, 'Password must contain a special character')

const requiredField = (fieldName) => yup.string().required(`${fieldName} is required`);

export {
    validateEmail,
    requiredField,
    validatePassword,
}