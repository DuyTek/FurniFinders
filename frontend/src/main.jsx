import { Provider } from 'react-redux';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import React, { Suspense, StrictMode } from 'react';
import { HelmetProvider } from 'react-helmet-async';

import App from './app';
import { store } from './reducer/store';

// ----------------------------------------------------------------------

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <StrictMode>
    <HelmetProvider>
      <BrowserRouter>
        <Suspense>
          <Provider store={store}>
            <App />
          </Provider>
        </Suspense>
      </BrowserRouter>
    </HelmetProvider>
  </StrictMode>
);
