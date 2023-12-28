import React from 'react';
import PropTypes from 'prop-types';

import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import Card from '@mui/material/Card';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';

import Label from '../../components/label';
import { fCurrency } from '../../utils/format-number';
import { sliderMarks } from '../../components/modal/add-product-modal';
// ----------------------------------------------------------------------

export default function ShopProductCard({ product }) {
  const renderStatus = (
    <Label
      variant="filled"
      color={(product.product_status === 'AVAILABLE' && 'success') || 'error'}
      sx={{
        zIndex: 9,
        top: 16,
        right: 16,
        position: 'absolute',
        textTransform: 'uppercase',
      }}
    >
      {product.product_status}
    </Label>
  );

  const renderImg = (
    <Box
      component="img"
      alt={product.product_name}
      src={product.image}
      sx={{
        top: 0,
        width: 1,
        height: 1,
        objectFit: 'cover',
        position: 'absolute',
      }}
    />
  );

  const renderPrice = (
    <Typography variant="subtitle1">
      {fCurrency(product.product_price)}
    </Typography>
  );
  const productChosen = sliderMarks.find((mark) => mark.value === product.product_percentage);
  return (
    <Card>
      <Box sx={{ pt: '100%', position: 'relative' }}>
        {product.product_status && renderStatus}

        {renderImg}
      </Box>

      <Stack spacing={2} sx={{ p: 3 }}>
        <Link color="inherit" underline="hover" variant="subtitle2" noWrap>
          {product.product_name}
        </Link>

        <Stack direction="row" alignItems="center" justifyContent="space-between">
          <Label color={productChosen.color}>{productChosen.label}</Label>
          {renderPrice}
        </Stack>
      </Stack>
    </Card>
  );
}

ShopProductCard.propTypes = {
  product: PropTypes.object,
};
