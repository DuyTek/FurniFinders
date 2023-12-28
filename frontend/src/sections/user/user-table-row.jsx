import PropTypes from 'prop-types';
import React, { useState } from 'react';
import { enqueueSnackbar } from 'notistack';

import { Button } from '@mui/material';
import Stack from '@mui/material/Stack';
import Avatar from '@mui/material/Avatar';
import Popover from '@mui/material/Popover';
import TableRow from '@mui/material/TableRow';
import Checkbox from '@mui/material/Checkbox';
import MenuItem from '@mui/material/MenuItem';
import TableCell from '@mui/material/TableCell';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import { Male, Female, HorizontalRule } from '@mui/icons-material';

import Label from '../../components/label';
import Iconify from '../../components/iconify';
import { verifyUser } from '../../service/admin';
import CustomDialog from '../../components/CustomDialog';

// ----------------------------------------------------------------------

export default function UserTableRow({
  id,
  selected,
  name,
  avatarUrl,
  company,
  phone,
  gender,
  status,
  handleClick,
}) {
  const [open, setOpen] = useState(null);
  const [openDialog, setOpenDialog] = useState(false);

  const handleOpenDialog = () => {
    setOpenDialog(true);
  }

  const handleOpenMenu = (event) => {
    setOpen(event.currentTarget);
  };

  const handleCloseMenu = () => {
    setOpen(null);
  };

  const handleVerifyUser = () => {
    verifyUser(id).then((response) => {
      if (response.status === 200) {
        setOpenDialog(false);
        enqueueSnackbar('User verified successfully', { variant: 'success' });
        handleClick();
      }
    }).catch((error) => {
      enqueueSnackbar(error.response.data, { variant: 'error' });
    });
  }


  return (
    <>
      <TableRow hover tabIndex={-1} role="checkbox" selected={selected}>
        <TableCell padding="checkbox">
          <Checkbox disableRipple checked={selected} onChange={handleClick} />
        </TableCell>

        <TableCell component="th" scope="row" padding="none">
          <Stack direction="row" alignItems="center" spacing={2}>
            <Avatar alt={name} src={avatarUrl} />
            <Typography variant="subtitle2" noWrap>
              {name}
            </Typography>
          </Stack>
        </TableCell>

        <TableCell>{company}</TableCell>

        <TableCell>{phone}</TableCell>

        <TableCell align="center">
          {gender === 'FEMALE' && <Female />}
          {gender === 'MALE' && <Male />}
          {gender === 'OTHERS' && <HorizontalRule />}
        </TableCell>

        <TableCell>
          <Label color={status === 'YES' ? 'success' : 'error'}>{status}</Label>
        </TableCell>

        <TableCell align="right">
          <IconButton onClick={handleOpenMenu}>
            <Iconify icon="eva:more-vertical-fill" />
          </IconButton>
        </TableCell>
      </TableRow>

      <Popover
        open={!!open}
        anchorEl={open}
        onClose={handleCloseMenu}
        anchorOrigin={{ vertical: 'top', horizontal: 'left' }}
        transformOrigin={{ vertical: 'top', horizontal: 'right' }}
        slotProps={{
          sx: { width: 140 },
        }}
      >
        <MenuItem onClick={handleCloseMenu}>
          <Iconify icon="eva:edit-fill" sx={{ mr: 2 }} />
          Edit
        </MenuItem>

        <MenuItem onClick={handleOpenDialog} sx={{ color: status === 'YES' ? 'error.main' : 'success.main' }}>
          <Iconify icon="eva:trash-2-outline" sx={{ mr: 2 }} />
          {status === 'YES' ? 'Deactivate' : 'Activate'}
        </MenuItem>

        <CustomDialog
          open={openDialog}
          handleClose={() => setOpenDialog(false)}
          title='Delete User'
          content='Are you sure you want to delete this user?'
          action={<>
            <Button onClick={() => setOpenDialog(false)} color="inherit">Cancel</Button>
            <Button onClick={handleVerifyUser} color="primary">Confirm</Button>
          </>}
          maxWidth='xs'
        />
      </Popover>
    </>
  );
}

UserTableRow.propTypes = {
  id: PropTypes.any,
  avatarUrl: PropTypes.any,
  company: PropTypes.any,
  handleClick: PropTypes.func,
  gender: PropTypes.any,
  name: PropTypes.any,
  phone: PropTypes.any,
  selected: PropTypes.any,
  status: PropTypes.string,
};
