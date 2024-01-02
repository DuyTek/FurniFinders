import React from 'react';
import PropTypes from 'prop-types';
import { useSelector } from 'react-redux';
import { enqueueSnackbar } from 'notistack';

import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import Card from '@mui/material/Card';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

import Label from '../../components/label';
import { buyProduct } from '../../service/user';
import { fCurrency } from '../../utils/format-number';
import CustomDialog from '../../components/CustomDialog';
import { updateProductStatus } from '../../service/admin';
import { sliderMarks } from '../../components/modal/add-product-modal';
// ----------------------------------------------------------------------

export default function ShopProductCard({ product, handleCallback }) {
  const [openDialog, setOpenDialog] = React.useState(false);
  const auth = useSelector((state) => state.auth);
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

  const productDialogContent = (<>
    <Typography variant="subtitle1" sx={{ mb: 2 }}>
      {product.product_name}
      <Label color={productChosen.color}>{productChosen.label}</Label>
    </Typography>
    <Typography variant="body2" sx={{ mb: 2 }}>
      {product.product_description}
    </Typography>
    <Stack direction="row" alignItems="center" justifyContent="space-between">
      {renderPrice}
    </Stack>

  </>)

  const handleValidateAuthenticationOnCard = () => {
    if (!auth.isAuthenticated) {
      setOpenDialog(false);
      return;
    }
    setOpenDialog(true);
  }

  const handleClickSecondary = () => {
    if (auth.user_role === 'ADMIN') {
      updateProductStatus(product.product_id, 'REJECTED').then((response) => {
        if (response.status === 200) {
          setOpenDialog(false);
          enqueueSnackbar('Product rejected', { variant: 'warning' })
          handleCallback();
        }
      });
    }
    setOpenDialog(false);
  }

  const handleClickPrimary = () => {
    if (auth.user_role === 'USER') {
      buyProduct(product.product_id).then((response) => {
        if (response.status === 200) {
          setOpenDialog(false);
          enqueueSnackbar('Product bought', { variant: 'success' })
          handleCallback();
        }
      })
      setOpenDialog(false);
    } else {
      updateProductStatus(product.product_id, 'APPROVED').then((response) => {
        setOpenDialog(false);
        enqueueSnackbar('Product approved', { variant: 'success' });
        handleCallback();
      });
    }
  }

  const handleCloseDialog = () => {
    setOpenDialog(false);
  }
  return (
    <Card onClick={handleValidateAuthenticationOnCard}>
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
        <CustomDialog
          open={openDialog}
          title='Product Details'
          content={productDialogContent}
          handleClose={handleCloseDialog}
          action={product.product_status !== 'SOLD' && (<>
            <Button color='error' variant='outlined' onClick={handleClickSecondary}>{auth.user_role === 'USER' ? 'Cancel' : 'Reject'}</Button>
            <Button variant='contained' onClick={handleClickPrimary}>{auth.user_role === 'USER' ? 'Buy' : 'Approve'}</Button>
          </>)
          }
          maxWidth='xs'
        />
      </Stack>
    </Card>
  );
}

ShopProductCard.propTypes = {
  product: PropTypes.object,
  handleCallback: PropTypes.func
};
