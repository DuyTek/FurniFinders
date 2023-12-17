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
};

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        authStart: (state) => {
            state.isLoading = true;
        },
        authSuccess: (state, action) => {
            state.token = action.payload.token;
            state.isAuthenticated = true;
            state.isLoading = false;
            state.user_first_name = action.payload.user_first_name;
            state.user_last_name = action.payload.user_last_name;
            state.user_phone = `0${action.payload.user_phone}`;
            state.user_email = action.payload.user_email;
            state.user_role = action.payload.user_role;
            state.user_id = action.payload.user_id;
        },
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
        },
    },
});
export default authSlice.reducer;
export const { authStart, authSuccess, authEnd, authLogout } = authSlice.actions;