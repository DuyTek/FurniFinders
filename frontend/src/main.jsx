import { Provider } from 'react-redux';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import React, { Suspense, StrictMode } from 'react';
import { HelmetProvider } from 'react-helmet-async';
import { PersistGate } from 'redux-persist/lib/integration/react';

import App from './app';
import { store, persistor } from './reducer/store';
import CustomSnackbar from './routes/components/styled-components/CustomSnackbar';

// ----------------------------------------------------------------------

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <StrictMode>
    <HelmetProvider>
      <BrowserRouter>
        <Suspense>
          <Provider store={store}>
            <PersistGate persistor={persistor}>
              <CustomSnackbar>
                <App />
              </CustomSnackbar>
            </PersistGate>
          </Provider>
        </Suspense>
      </BrowserRouter>
    </HelmetProvider>
  </StrictMode>
);
