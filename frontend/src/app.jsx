/* eslint-disable perfectionist/sort-imports */
import React from 'react';
import './global.css';
import ThemeProvider from './theme';
import Router from './routes/sections';
import { useScrollToTop } from './hooks/use-scroll-to-top';


// ----------------------------------------------------------------------

export default function App() {
  useScrollToTop();

  return (
    <ThemeProvider>
      <Router />
    </ThemeProvider>
  );
}
