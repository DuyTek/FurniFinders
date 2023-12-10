import React from 'react';
import PropTypes from 'prop-types';

import Input from '@mui/material/Input';
import Button from '@mui/material/Button';
import { styled } from '@mui/material/styles';
import InputAdornment from '@mui/material/InputAdornment';

import { bgBlur } from '../../../theme/css';
import Iconify from '../../../components/iconify';

// ----------------------------------------------------------------------

const HEADER_MOBILE = 32;
const HEADER_DESKTOP = 64;

const StyledSearchbar = styled('div')(({ theme }) => ({
  ...bgBlur({
    color: theme.palette.background.default,
  }),
  zIndex: 99,
  width: '100%',
  display: 'flex',
  alignItems: 'center',
  height: HEADER_MOBILE,
  [theme.breakpoints.up('md')]: {
    height: HEADER_DESKTOP,
  },
}));

// ----------------------------------------------------------------------

export default function Searchbar({ value, onChange }) {

  return (
    <div>
      <StyledSearchbar>
        <Input
          autoFocus
          fullWidth
          disableUnderline
          placeholder="Search…"
          value={value}
          onChange={onChange}
          startAdornment={
            <InputAdornment position="start">
              <Iconify
                icon="eva:search-fill"
                sx={{ color: 'text.disabled', width: 20, height: 20 }}
              />
            </InputAdornment>
          }
          sx={{ mr: 1, fontWeight: 'fontWeightBold' }}
        />
        <Button variant="contained">
          Search
        </Button>
      </StyledSearchbar>
    </div>
  );
}

Searchbar.propTypes = {
  value: PropTypes.string,
  onChange: PropTypes.func,
}
