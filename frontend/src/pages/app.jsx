import React from 'react';
import { Helmet } from 'react-helmet-async';

import { AppView } from '../sections/overview/view';

// ----------------------------------------------------------------------

export default function AppPage() {
  return (
    <>
      <Helmet>
        <title> Dashboard | Wood </title>
      </Helmet>

      <AppView />
    </>
  );
}
