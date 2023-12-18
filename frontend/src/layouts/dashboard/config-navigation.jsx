import React from 'react';

import SvgColor from '../../components/svg-color';
import { USER_ROLES } from '../../constants/constants';
import { USER_PRODUCTS, ADMIN_DASHBOARD } from '../../constants/router-link';

// ----------------------------------------------------------------------

const icon = (name) => (
  <SvgColor src={`/assets/icons/navbar/${name}.svg`} sx={{ width: 1, height: 1 }} />
);

const navConfig = [
  {
    title: 'posts',
    path: '/blog',
    icon: icon('ic_blog'),
    roles: [USER_ROLES.USER]
  },
  {
    title: 'dashboard',
    path: ADMIN_DASHBOARD,
    icon: icon('ic_analytics'),
    roles: [USER_ROLES.ADMIN]
  },
  {
    title: 'user',
    path: '/admin/users',
    icon: icon('ic_user'),
    roles: [USER_ROLES.ADMIN]
  },
  {
    title: 'products',
    path: USER_PRODUCTS,
    icon: icon('ic_cart'),
    roles: [USER_ROLES.USER]
  },
];

export default navConfig;
