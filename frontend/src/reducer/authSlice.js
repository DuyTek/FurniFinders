import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    token: null,
    isAuthenticated: false,
    isLoading: false,
    firtName: null,
    lastName: null,
    userPhone: null,
    userEmail: null,
    userRole: null,
    userId: null,
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
            state.firtName = action.payload.firtName;
            state.lastName = action.payload.lastName;
            state.userPhone = action.payload.userPhone;
            state.userEmail = action.payload.userEmail;
            state.userRole = action.payload.userRole;
            state.userId = action.payload.userId;
        },
        authEnd: (state) => {
            state.isLoading = false;
        },
        authLogout: (state) => {
            state.token = null;
            state.isAuthenticated = false;
            state.isLoading = false;
            state.firtName = null;
            state.lastName = null;
            state.userPhone = null;
            state.userEmail = null;
            state.userRole = null;
            state.userId = null;
        },
    },
});
export default authSlice.reducer;
export const { authStart, authSuccess, authEnd, authLogout } = authSlice.actions;