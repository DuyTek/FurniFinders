import React from 'react';
import PropTypes from 'prop-types';
import {
    closeSnackbar,
    SnackbarProvider,
    MaterialDesignContent,
} from 'notistack';

import { Close } from '@mui/icons-material';
import {
    Fade,
    styled,
    IconButton,
} from '@mui/material';

export default function CustomSnackbar({ children }) {
    const closeSnackbarButton = (
        <IconButton onClick={() => closeSnackbar()}>
            <Close sx={{ color: 'white' }} />
        </IconButton>
    );

    const StyledMaterialDesignContent = styled(MaterialDesignContent)(() => ({
        '&.notistack-MuiContent-success': {
            backgroundColor: 'green',
        },
        '&.notistack-MuiContent-error': {
            backgroundColor: 'red',
        },
    }));

    return (
        <SnackbarProvider
            Components={{
                success: StyledMaterialDesignContent,
                error: StyledMaterialDesignContent,
            }}
            autoHideDuration={3000}
            TransitionComponent={Fade}
            anchorOrigin={{
                vertical: 'top',
                horizontal: 'center',
            }}
            hideIconVariant
            maxSnack={2}
            action={closeSnackbarButton}
        >
            {children}
        </SnackbarProvider>
    );
}

CustomSnackbar.propTypes = {
    children: PropTypes.node.isRequired,
}
