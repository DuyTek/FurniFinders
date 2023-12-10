import { configureStore, combineReducers } from "@reduxjs/toolkit";

import authSlice from "./authSlice";

const appReducer = combineReducers({
    auth: authSlice
});

export const store = configureStore({
    reducer: appReducer,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        serializableCheck: false,
    }),
});
