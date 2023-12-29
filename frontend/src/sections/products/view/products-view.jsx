import React, { useState, useEffect } from 'react';

import { Button } from '@mui/material';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Unstable_Grid2';
import Typography from '@mui/material/Typography';

import ProductCard from '../product-card';
import Iconify from '../../../components/iconify';
import { getAllApprovedProducts } from '../../../service/product';
import Searchbar from '../../../layouts/dashboard/common/searchbar';
import AddProductModal from '../../../components/modal/add-product-modal';

// ----------------------------------------------------------------------

export default function ProductsView() {
  const [searchValue, setSearchValue] = useState('');
  const [products, setProducts] = useState([]);
  const [openModal, setOpenModal] = useState(false);
  const [changed, setChanged] = useState(false);

  const handleChanges = (event) => {
    setChanged(!changed);
  }

  useEffect(() => {
    getAllApprovedProducts().then((response) => {
      const productsWithImages = response.data.map((product, index) => ({
        ...product,
        image: `/assets/images/products/${index + 1}.jpeg`,
      }));
      setProducts(productsWithImages);
    });
  }, [changed]);

  const filteredProducts = products.filter((product) =>
    product.product_name.toLowerCase().includes(searchValue.toLowerCase())
    && product.product_status === 'AVAILABLE'
  );

  return (
    <Container>
      <Stack direction="row" alignItems="center" justifyContent="space-between">
        <Typography variant="h4" sx={{ mb: 5 }}>
          Products
        </Typography>
        <Button
          variant="contained"
          color="inherit"
          startIcon={<Iconify icon="eva:plus-fill" />}
          onClick={() => setOpenModal(true)}
        >
          Sell something
        </Button>
        <AddProductModal open={openModal} onClose={() => setOpenModal(false)} />
      </Stack>

      <Searchbar value={searchValue} onChange={(e) => setSearchValue(e.target.value)} />

      <Grid container spacing={3}>
        {filteredProducts.map((product) => (
          <Grid key={product.product_id} xs={12} sm={6} md={3}>
            <ProductCard product={product} handleCallback={handleChanges} />
          </Grid>
        ))}
      </Grid>

    </Container>
  );
}
