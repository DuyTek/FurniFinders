import React from 'react';
import { Helmet } from 'react-helmet-async';

import { BlogView } from '../sections/blog/view';

// ----------------------------------------------------------------------

export default function BlogPage() {
  return (
    <>
      <Helmet>
        <title> Blog | Wood </title>
      </Helmet>

      <BlogView />
    </>
  );
}
