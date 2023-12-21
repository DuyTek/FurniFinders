import React from 'react';
import { Helmet } from 'react-helmet-async';

import ProfileView from '../sections/user/profile/profile-view';

// ----------------------------------------------------------------------

export default function UserPage() {
    return (
        <>
            <Helmet>
                <title> Profile </title>
            </Helmet>

            <ProfileView />
        </>
    );
}
