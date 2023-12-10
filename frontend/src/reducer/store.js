import storage from 'redux-persist/lib/storage';
import { persistStore, persistReducer } from 'redux-persist';
import { configureStore, combineReducers } from "@reduxjs/toolkit";
import autoMergeLevel2 from 'redux-persist/lib/stateReconciler/autoMergeLevel2';

import authSlice from "./authSlice";

const persisConfig = {
    key: 'root',
    storage,
    whitelist: ['auth'],
    stateReconciler: autoMergeLevel2,
}

const appReducer = combineReducers({
    auth: authSlice
});

const persistedReducer = persistReducer(persisConfig, appReducer);

export const store = configureStore({
    reducer: persistedReducer,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        serializableCheck: false,
    }),
});

export const persistor = persistStore(store);