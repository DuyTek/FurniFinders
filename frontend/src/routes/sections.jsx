import React, { lazy, Suspense } from 'react';
import { Outlet, Navigate, useRoutes } from 'react-router-dom';

import SignUpPage from '../pages/signup';
import DashboardLayout from '../layouts/dashboard';
import { USER_ROLES } from '../constants/constants';
import AuthRoute from '../components/auth/AuthRoute';
import PrivateRoute from '../components/auth/PrivateRoute';
import { HOMEPAGE, ADMIN_USERS, USER_PRODUCTS, ADMIN_DASHBOARD } from '../constants/router-link';

export const IndexPage = lazy(() => import('../pages/app'));
export const BlogPage = lazy(() => import('../pages/blog'));
export const UserPage = lazy(() => import('../pages/user'));
export const LoginPage = lazy(() => import('../pages/login'));
export const ProductsPage = lazy(() => import('../pages/products'));
export const Page404 = lazy(() => import('../pages/page-not-found'));

// ----------------------------------------------------------------------

export default function Router() {
  const routes = useRoutes([
    {
      element: (
        <AuthRoute>
          <DashboardLayout>
            <Suspense>
              <Outlet />
            </Suspense>
          </DashboardLayout>
        </AuthRoute>
      ),
      children: [
        { path: HOMEPAGE, element: <ProductsPage />, index: true },
      ]
    },
    {
      element: (
        <PrivateRoute requiredRole={[USER_ROLES.USER]}>
          <DashboardLayout>
            <Suspense>
              <Outlet />
            </Suspense>
          </DashboardLayout>
        </PrivateRoute>
      ),
      children: [
        { path: USER_PRODUCTS, element: <ProductsPage />, index: true },
        { path: 'blog', element: <BlogPage /> },
      ],
    },
    {
      element: (
        <PrivateRoute requiredRole={[USER_ROLES.ADMIN]}>
          <DashboardLayout>
            <Suspense>
              <Outlet />
            </Suspense>
          </DashboardLayout>
        </PrivateRoute>
      ),
      children: [
        { path: ADMIN_DASHBOARD, element: <IndexPage />, index: true },
        { path: ADMIN_USERS, element: <UserPage /> },
      ]
    },
    {
      path: 'signup',
      element:
        <AuthRoute>
          <SignUpPage />
        </AuthRoute>,
    },
    {
      path: 'login',
      element:
        <AuthRoute>
          <LoginPage />
        </AuthRoute>,
    },
    {
      path: '404',
      element: <Page404 />,
    },
    {
      path: '*',
      element: <Navigate to="/404" replace />,
    },
  ]);

  return routes;
}
