import React from 'react';
import { Helmet } from 'react-helmet-async';

import { SignUpView } from '../sections/signup';


// ----------------------------------------------------------------------

export default function SignUpPage() {
    return (
        <>
            <Helmet>
                <title> Sign Up | Wood </title>
            </Helmet>

            <SignUpView />
        </>
    );
}
