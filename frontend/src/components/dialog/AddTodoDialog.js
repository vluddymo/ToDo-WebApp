import React, {useState} from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';

export const AddTodoDialog = ({open, onClose, onAdd}) => {
    const [description, setDescription] = useState('');
    return (
        <Dialog open={open} onClose={() => onClose()} aria-labelledby="form-dialog-title">
            <DialogTitle id="form-dialog-title">Add</DialogTitle>
            <DialogContent>
                <TextField
                    autoFocus
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    margin="dense"
                    label="Description"
                    type="text"
                    fullWidth
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={() => onClose()} color="primary">
                    Cancel
                </Button>
                <Button onClick={() => onAdd(description)} color="primary">
                    Add
                </Button>
            </DialogActions>
        </Dialog>
    );
};
