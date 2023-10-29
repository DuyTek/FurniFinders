import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import React, { forwardRef } from 'react';

// ----------------------------------------------------------------------

const RouterLink = forwardRef(({ href, ...other }, ref) => <Link ref={ref} to={href} {...other} />);

RouterLink.propTypes = {
  href: PropTypes.string,
};

export default RouterLink;
