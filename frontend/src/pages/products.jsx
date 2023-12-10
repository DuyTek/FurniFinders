import React from 'react';
import { Helmet } from 'react-helmet-async';

import { ProductsView } from '../sections/products/view';

// ----------------------------------------------------------------------

export default function ProductsPage() {
  return (
    <>
      <Helmet>
        <title> Products | Wood </title>
      </Helmet>

      <ProductsView />
    </>
  );
}
