import 'dayjs/locale/en-gb'
import React, { useState, useEffect } from 'react';

import Card from '@mui/material/Card';
import Stack from '@mui/material/Stack';
import Table from '@mui/material/Table';
import Button from '@mui/material/Button';
import {
  TextField
} from '@mui/material';
import Container from '@mui/material/Container';
import TableBody from '@mui/material/TableBody';
import Typography from '@mui/material/Typography';
import TableContainer from '@mui/material/TableContainer';
import TablePagination from '@mui/material/TablePagination';

import UserTableRow from '../user-table-row';
import UserTableHead from '../user-table-head';
import Iconify from '../../../components/iconify';
import Scrollbar from '../../../components/scrollbar';
import { getUserList } from '../../../service/product';
import { combineName } from '../../../utils/stringHelper';
import AddUserDialog from '../../../components/AddUserDialog';


// ----------------------------------------------------------------------

// ----------------------------------------------------------------------
export default function UserPage() {
  const [page, setPage] = useState(0);
  const [selected, setSelected] = useState([]);
  const [userList, setUserList] = useState([]);
  const [openAddUserDialog, setOpenAddUserDialog] = useState(false);
  const [changed, setChanged] = useState(false);


  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelecteds = userList.map((n) => n.name);
      setSelected(newSelecteds);
      return;
    }
    setSelected([]);
  };

  const handleCloseDialog = () => {
    setOpenAddUserDialog(false);
  }

  const handleSearchUser = (event) => {
    const searchValue = event.target.value;
    if (searchValue === '') {
      getUserList().then((response) => {
        handleSetUserList(response.data);
      })
    } else {
      const filtered = userList.filter((user) => combineName(user.user_first_name, user.user_last_name).toLowerCase().includes(searchValue.toLowerCase()));
      setUserList(filtered);
    }
  }

  const handleClick = (event, name) => {
    const selectedIndex = selected.indexOf(name);
    let newSelected = [];
    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, name);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(
        selected.slice(0, selectedIndex),
        selected.slice(selectedIndex + 1)
      );
    }
    setSelected(newSelected);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleSetUserList = (users) => {
    const filtered = users.filter((user) => user.user_role === 'USER');
    setUserList(filtered);
  }

  useEffect(() => {
    getUserList().then((response) => {
      handleSetUserList(response.data);
    })
  }, [openAddUserDialog, changed])
  return (
    <Container>
      <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
        <Typography variant="h4">Users</Typography>

        <Button onClick={() => setOpenAddUserDialog(true)} variant="contained" color="inherit" startIcon={<Iconify icon="eva:plus-fill" />}>
          New User
        </Button>
      </Stack>
      <TextField placeholder='Search user...' sx={{ mb: 2 }} onChange={handleSearchUser} />

      <Card>
        <Scrollbar>
          <TableContainer sx={{ overflow: 'unset' }}>
            <Table sx={{ minWidth: 800 }}>
              <UserTableHead
                rowCount={userList.length}
                numSelected={selected.length}
                onSelectAllClick={handleSelectAllClick}
                headLabel={[
                  { id: 'name', label: 'Name' },
                  { id: 'email', label: 'Email' },
                  { id: 'phone', label: 'Phone' },
                  { id: 'gender', label: 'Gender', align: 'center' },
                  { id: 'verified', label: 'Verified' },
                  { id: '' },
                ]}
              />
              <TableBody>
                {userList
                  .slice(page * 6, page * 6 + 6)
                  .filter((row) => row.user_role === 'USER')
                  .map((row) => (
                    <UserTableRow
                      key={row.user_id}
                      name={combineName(row.user_first_name, row.user_last_name)}
                      id={row.user_id}
                      phone={`0${row.user_phone}`}
                      status={row.user_verified}
                      company={row.user_email}
                      gender={row.user_gender}
                      selected={selected.indexOf(row.name) !== -1}
                      handleClick={(event) => {
                        handleClick(event, row.user_email);
                        setChanged(!changed);
                      }}
                      user={row}
                    />
                  ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Scrollbar>

        <TablePagination
          page={page}
          component="div"
          count={userList.length}
          rowsPerPage={6}
          onPageChange={handleChangePage}
          rowsPerPageOptions={[]}
        />
      </Card>
      {openAddUserDialog && <AddUserDialog open={openAddUserDialog} handleClose={handleCloseDialog} />}
    </Container>
  );
}
