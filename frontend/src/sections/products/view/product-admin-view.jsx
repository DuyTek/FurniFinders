import React, { useState, useEffect } from 'react';

import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Unstable_Grid2';
import Typography from '@mui/material/Typography';

import ProductCard from '../product-card';
import ProductFilters from '../product-filters';
import { getAllProducts } from '../../../service/product';
import Searchbar from '../../../layouts/dashboard/common/searchbar';
import AddProductModal from '../../../components/modal/add-product-modal';

// ----------------------------------------------------------------------

export default function ProductAdminView() {
    const [openFilter, setOpenFilter] = useState(false);
    const [searchValue, setSearchValue] = useState('');
    const [products, setProducts] = useState([]);
    const [openModal, setOpenModal] = useState(false);
    const [selectedCategory, setSelectedCategory] = useState(null);
    const [selectedPrice, setSelectedPrice] = useState(null);
    const [selectedProductStatus, setSelectedProductStatus] = useState('PENDING');
    const [changed, setChanged] = useState(false);

    const handleOpenFilter = () => {
        setOpenFilter(true);
    };

    const handleCloseFilter = () => {
        setOpenFilter(false);
    };

    const handleChooseFilter = (event) => {
        setSelectedCategory(event.target.value);
    }

    const handleFilterPrice = (event) => {
        setSelectedPrice(event.target.value);
    }

    const handleFilterProductStatus = (event) => {
        setSelectedProductStatus(event.target.value);
    }
    const handleClearAll = () => {
        setSelectedCategory(null);
        setSelectedPrice(null);
        setOpenFilter(false);
        window.location.reload();
    }
    const handleCallback = () => {
        setChanged(!changed);
    }
    useEffect(() => {
        getAllProducts().then((response) => {
            const productsWithImages = response.data.map((product, index) => ({
                ...product,
                image: `/assets/images/products/${index + 1}.jpeg`,
            }));
            setProducts(productsWithImages);
        });
    }, [changed]);

    const filteredProducts = products.filter((product) => {
        if (selectedPrice !== null) {
            return product.product_name.toLowerCase().includes(searchValue.toLowerCase())
                && product.product_price <= selectedPrice
        }
        if (selectedProductStatus !== null) {
            return product.product_name.toLowerCase().includes(searchValue.toLowerCase())
                && product.product_post_status === selectedProductStatus
        }

        if (selectedCategory !== null) {
            return product.product_name.toLowerCase().includes(searchValue.toLowerCase())
                && product.product_percentage >= selectedCategory
        }
        return product.product_name.toLowerCase().includes(searchValue.toLowerCase());
    }
    );


    return (
        <Container>
            <Stack direction="row" alignItems="center" justifyContent="space-between">
                <Typography variant="h4" sx={{ mb: 5 }}>
                    Products
                </Typography>
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
                        onFilterChange={handleChooseFilter}
                        onFilterPrice={handleFilterPrice}
                        onFilterStatus={handleFilterProductStatus}
                        onClear={handleClearAll}
                    />

                </Stack>
            </Stack>

            <Grid container spacing={3}>
                {filteredProducts.map((product) => product.product_status !== 'SOLD' && (
                    <Grid key={product.product_id} xs={12} sm={6} md={3}>
                        <ProductCard product={product} handleCallback={handleCallback} />
                    </Grid>
                ))}
            </Grid>

        </Container>
    );
}
