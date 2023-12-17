import PropTypes from "prop-types";
import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";

import { HOMEPAGE } from "../../constants/router-link";

export default function PrivateRoute({ requiredRole, children }) {
    const { isAuthenticated, user_role } = useSelector((state) => state.auth);

    if (!isAuthenticated) {
        return <Navigate to={HOMEPAGE} />;
    }

    const userHasRequiredRole = !!(user_role && requiredRole?.includes(user_role));
    if (!userHasRequiredRole) {
        return <Navigate to='/404' />;
    }

    return children;
}

PrivateRoute.propTypes = {
    children: PropTypes.node.isRequired,
    requiredRole: PropTypes.array,
};
