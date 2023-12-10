import React from 'react';
import PropTypes from 'prop-types';
import { useSelector } from 'react-redux';

import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import AppBar from '@mui/material/AppBar';
import { Typography } from '@mui/material';
import Toolbar from '@mui/material/Toolbar';
import { useTheme } from '@mui/material/styles';
import IconButton from '@mui/material/IconButton';

import { bgBlur } from '../../theme/css';
import Searchbar from './common/searchbar';
import { NAV, HEADER } from './config-layout';
import { useRouter } from '../../routes/hooks';
import Iconify from '../../components/iconify';
import AccountPopover from './common/account-popover';
import { useResponsive } from '../../hooks/use-responsive';

// ----------------------------------------------------------------------
const UserMenu = ({ username }) => (
  <Stack direction='row' alignItems='center' spacing={2}>
    <Typography variant='h7' color='black'>Hello, {username}</Typography>
    <AccountPopover />
  </Stack>
);

UserMenu.propTypes = {
  username: PropTypes.string,
};
export default function Header({ onOpenNav }) {
  const theme = useTheme();
  const router = useRouter();
  const lgUp = useResponsive('up', 'lg');
  const { user_first_name } = useSelector((state) => state.auth);
  const renderContent = (
    <>
      {!lgUp && (
        <IconButton onClick={onOpenNav} sx={{ mr: 1 }}>
          <Iconify icon="eva:menu-2-fill" />
        </IconButton>
      )}

      <Searchbar />

      <Box sx={{ flexGrow: 1 }} />

      <Stack direction="row" alignItems="center" spacing={1}>
        {
          user_first_name
            ? <UserMenu username={user_first_name} />
            :
            <Button variant='contained' onClick={() => router.push('/login')}>
              Sign in
            </Button>
        }
      </Stack>
    </>
  );

  return (
    <AppBar
      sx={{
        boxShadow: 'none',
        height: HEADER.H_MOBILE,
        zIndex: theme.zIndex.appBar + 1,
        ...bgBlur({
          color: theme.palette.background.default,
        }),
        transition: theme.transitions.create(['height'], {
          duration: theme.transitions.duration.shorter,
        }),
        ...(lgUp && {
          width: `calc(100% - ${NAV.WIDTH + 1}px)`,
          height: HEADER.H_DESKTOP,
        }),
      }}
    >
      <Toolbar
        sx={{
          height: 1,
          px: { lg: 5 },
        }}
      >
        {renderContent}
      </Toolbar>
    </AppBar>
  );
}

Header.propTypes = {
  onOpenNav: PropTypes.func,
};
