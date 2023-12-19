import React from "react";
import PropTypes from "prop-types";
import { Controller, useFormContext } from "react-hook-form";

import { TextField, FormControl, FormHelperText, InputAdornment } from "@mui/material";

export default function CustomTextField({
    name,
    label,
    required,
    type,
    multiline = false,
    rows = 1,
    startAdornment,
    fullWidth,
    ...rest
}) {
    const { control } = useFormContext();
    return (
        <Controller
            control={control}
            name={name}
            render={({ fieldState: { error }, field }) =>
                <FormControl margin="normal">
                    <TextField
                        InputProps={{
                            startAdornment: <InputAdornment position="start">{startAdornment}</InputAdornment>,
                        }}
                        value={field.value}
                        ref={field.ref}
                        name={field.name}
                        {...field}
                        label={label}
                        required={required}
                        error={Boolean(error?.message)}
                        onChange={field.onChange}
                        fullWidth={fullWidth}
                        multiline={multiline}
                        rows={rows}
                        type={type}
                        {...rest}
                    />
                    <FormHelperText>{error ? error.message : ''}</FormHelperText>
                </FormControl>
            }
        />
    )
}

CustomTextField.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    startAdornment: PropTypes.element,
    required: PropTypes.bool,
    type: PropTypes.string,
    multiline: PropTypes.bool,
    rows: PropTypes.number,
    fullWidth: PropTypes.bool,
    rest: PropTypes.any,
}