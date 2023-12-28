import React, { useState, useEffect } from 'react';

import { Button } from '@mui/material';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Unstable_Grid2';
import Typography from '@mui/material/Typography';

import ProductCard from '../product-card';
import ProductSort from '../product-sort';
import ProductFilters from '../product-filters';
import Iconify from '../../../components/iconify';
import ProductCartWidget from '../product-cart-widget';
import { getAllApprovedProducts } from '../../../service/product';
import Searchbar from '../../../layouts/dashboard/common/searchbar';
import AddProductModal from '../../../components/modal/add-product-modal';

// ----------------------------------------------------------------------

export default function ProductsView() {
  const [openFilter, setOpenFilter] = useState(false);
  const [searchValue, setSearchValue] = useState('');
  const [products, setProducts] = useState([]);
  const [openModal, setOpenModal] = useState(false);

  const handleOpenFilter = () => {
    setOpenFilter(true);
  };

  const handleCloseFilter = () => {
    setOpenFilter(false);
  };

  useEffect(() => {
    getAllApprovedProducts().then((response) => {
      const productsWithImages = response.data.map((product, index) => ({
        ...product,
        image: `/assets/images/products/${index + 1}.jpeg`,
      }));
      setProducts(productsWithImages);
    });
  }, []);

  const filteredProducts = products.filter((product) =>
    product.product_name.toLowerCase().includes(searchValue.toLowerCase())
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
      <Stack
        direction="row"
        alignItems="center"
        flexWrap="wrap-reverse"
        justifyContent="flex-end"
        sx={{ mb: 5 }}
      >
        <Stack direction="row" spacing={1} flexShrink={0} sx={{ my: 1 }}>
          <ProductFilters
            openFilter={openFilter}
            onOpenFilter={handleOpenFilter}
            onCloseFilter={handleCloseFilter}
          />

          <ProductSort />
        </Stack>
      </Stack>

      <Grid container spacing={3}>
        {filteredProducts.map((product) => (
          <Grid key={product.product_id} xs={12} sm={6} md={3}>
            <ProductCard product={product} />
          </Grid>
        ))}
      </Grid>

      <ProductCartWidget />
    </Container>
  );
}
