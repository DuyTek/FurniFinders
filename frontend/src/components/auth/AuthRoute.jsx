import React from 'react';
import PropTypes from 'prop-types';
import { useSelector } from 'react-redux';
import { Navigate } from 'react-router-dom';

import { USER_ROLES } from '../../constants/constants';
import { USER_PRODUCTS, ADMIN_PRODUCTS } from '../../constants/router-link';

export default function AuthRoute({ children }) {
    const { isAuthenticated, user_role } = useSelector(
        (state) => state.auth,
    );

    if (isAuthenticated) {
        if (user_role === USER_ROLES.ADMIN) return <Navigate to={ADMIN_PRODUCTS} />;
        if (user_role === USER_ROLES.USER) return <Navigate to={USER_PRODUCTS} />;
    }

    return children;
}

AuthRoute.propTypes = {
    children: PropTypes.node.isRequired,
}