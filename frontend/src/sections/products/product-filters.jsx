import React from 'react';
import PropTypes from 'prop-types';

import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import Radio from '@mui/material/Radio';
import Button from '@mui/material/Button';
import Drawer from '@mui/material/Drawer';
import Divider from '@mui/material/Divider';
import RadioGroup from '@mui/material/RadioGroup';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import FormControlLabel from '@mui/material/FormControlLabel';

import Iconify from '../../components/iconify';
import Scrollbar from '../../components/scrollbar';
import { sliderMarks } from '../../components/modal/add-product-modal';

// ----------------------------------------------------------------------

export const SORT_OPTIONS = [
  { value: 'featured', label: 'Featured' },
  { value: 'newest', label: 'Newest' },
  { value: 'priceDesc', label: 'Price: High-Low' },
  { value: 'priceAsc', label: 'Price: Low-High' },
];
export const PRICE_OPTIONS = [
  { value: 100, label: 'Below 100' },
  { value: 1000, label: 'Below $1000' },
  { value: 2000, label: 'Below $2000' },
];

export const PRODUCT_STATUS = [
  { value: 'APPROVED', label: 'Approved' },
  { value: 'PENDING', label: 'Pending' },
  { value: 'REJECTED', label: 'Rejected' },
]

// ----------------------------------------------------------------------

export default function ProductFilters({ openFilter, onOpenFilter, onCloseFilter, onFilterChange, onFilterPrice, onClear, onFilterStatus }) {

  const renderProductStatus = (
    <Stack spacing={1}>
      <Typography variant="subtitle2">Request types</Typography>
      <RadioGroup>
        {PRODUCT_STATUS.map((item) => (
          <FormControlLabel key={item.value} onChange={onFilterStatus} value={item.value} control={<Radio />} label={item.label} />
        ))}
      </RadioGroup>
    </Stack>
  )
  const renderCategory = (
    <Stack spacing={1}>
      <Typography variant="subtitle2">Quality (Greater than)</Typography>
      <RadioGroup>
        {sliderMarks.map((item) => (
          <FormControlLabel key={item.value} onChange={onFilterChange} value={item.value} control={<Radio />} label={item.label} />
        ))}
      </RadioGroup>
    </Stack>
  );

  const renderPrice = (
    <Stack spacing={1}>
      <Typography variant="subtitle2">Price</Typography>
      <RadioGroup>
        {PRICE_OPTIONS.map((item) => (
          <FormControlLabel
            onChange={onFilterPrice}
            key={item.value}
            value={item.value}
            control={<Radio />}
            label={item.label}
          />
        ))}
      </RadioGroup>
    </Stack>
  );



  return (
    <>
      <Button
        disableRipple
        color="inherit"
        endIcon={<Iconify icon="ic:round-filter-list" />}
        onClick={onOpenFilter}
      >
        Filters&nbsp;
      </Button>

      <Drawer
        anchor="right"
        open={openFilter}
        onClose={onCloseFilter}
        PaperProps={{
          sx: { width: 280, border: 'none', overflow: 'hidden' },
        }}
      >
        <Stack
          direction="row"
          alignItems="center"
          justifyContent="space-between"
          sx={{ px: 1, py: 2 }}
        >
          <Typography variant="h6" sx={{ ml: 1 }}>
            Filters
          </Typography>
          <IconButton onClick={onCloseFilter}>
            <Iconify icon="eva:close-fill" />
          </IconButton>
        </Stack>

        <Divider />

        <Scrollbar>
          <Stack spacing={3} sx={{ p: 3 }}>
            {renderProductStatus}

            {renderCategory}

            {renderPrice}

          </Stack>
        </Scrollbar>

        <Box sx={{ p: 3 }}>
          <Button
            fullWidth
            size="large"
            type="submit"
            color="inherit"
            variant="outlined"
            startIcon={<Iconify icon="ic:round-clear-all" />}
            onClick={onClear}
          >
            Clear All
          </Button>
        </Box>
      </Drawer>
    </>
  );
}

ProductFilters.propTypes = {
  openFilter: PropTypes.bool,
  onOpenFilter: PropTypes.func,
  onCloseFilter: PropTypes.func,
  onFilterChange: PropTypes.func,
  onFilterPrice: PropTypes.func,
  onClear: PropTypes.func,
  onFilterStatus: PropTypes.func,
};
