import PropTypes from 'prop-types';
import React, { forwardRef } from 'react';

import Box from '@mui/material/Box';
import Link from '@mui/material/Link';

import { RouterLink } from '../../routes/components';
import { USER_PRODUCTS } from '../../constants/router-link';
import woodlogo from '../../../public/assets/wood-logo.svg';

// ----------------------------------------------------------------------

const Logo = forwardRef(({ disabledLink = false, sx, ...other }, ref) => {

  const logo = (
    <Box
      component="img"
      src={woodlogo}
      sx={{ width: 100, height: 100, cursor: 'pointer', ...sx }}
    />
  );


  if (disabledLink) {
    return logo;
  }

  return (
    <Link component={RouterLink} href={USER_PRODUCTS} sx={{ display: 'contents' }}>
      {logo}
    </Link>
  );
});

Logo.propTypes = {
  disabledLink: PropTypes.bool,
  sx: PropTypes.object,
};

export default Logo;
