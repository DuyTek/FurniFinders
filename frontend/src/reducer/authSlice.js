import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    token: null,
    isAuthenticated: false,
    isLoading: false,
    user_first_name: null,
    user_last_name: null,
    user_phone: null,
    user_email: null,
    user_role: null,
    user_id: null,
    user_address: null,
};

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        authStart: (state) => {
            state.isLoading = true;
        },
        authSuccess: (state, action) => ({
            ...state,
            token: action.payload.token,
            isAuthenticated: true,
            isLoading: false,
            user_first_name: action.payload.user_first_name,
            user_last_name: action.payload.user_last_name,
            user_phone: action.payload.user_phone,
            user_email: action.payload.user_email,
            user_role: action.payload.user_role,
            user_id: action.payload.user_id,
            user_address: action.payload.user_address,
        }),
        authEnd: (state) => {
            state.isLoading = false;
        },
        authLogout: (state) => {
            state.token = null;
            state.isAuthenticated = false;
            state.isLoading = false;
            state.user_first_name = null;
            state.user_last_name = null;
            state.user_phone = null;
            state.user_email = null;
            state.user_role = null;
            state.user_id = null;
            state.user_address = null;
        },
        update: (state, action) => ({
            ...state,
            user_first_name: action.payload.user_first_name,
            user_last_name: action.payload.user_last_name,
            user_phone: action.payload.user_phone,
            user_email: action.payload.user_email,
            user_address: action.payload.user_address,
        }),
    },
});
export default authSlice.reducer;
export const { authStart, authSuccess, authEnd, authLogout, update } = authSlice.actions;