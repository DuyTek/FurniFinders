import React from 'react';
import PropTypes from 'prop-types';

import { Dialog, DialogTitle, DialogContent, DialogActions } from "@mui/material";

export default function CustomDialog({ title, content, action, open, handleClose, ...other }) {
    return (
        <Dialog
            open={open}
            onClose={handleClose}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
            fullWidth
            {...other}
        >
            <DialogTitle id="alert-dialog-title">{title}</DialogTitle>
            <DialogContent>
                {content}
            </DialogContent>
            <DialogActions>
                {action}
            </DialogActions>
        </Dialog>
    )
}

CustomDialog.propTypes = {
    other: PropTypes.any,
    title: PropTypes.string,
    content: PropTypes.any,
    action: PropTypes.element,
    open: PropTypes.bool,
    handleClose: PropTypes.func,
}