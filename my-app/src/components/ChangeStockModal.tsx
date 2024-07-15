// src/components/ChangeStockModal.tsx
        import React, { useState } from 'react';

        const ChangeStockModal = ({ onChange, onClose }) => {
        const [symbol, setSymbol] = useState('');

        const handleSubmit = () => {
        onChange(symbol);
        onClose();
        };

        return (
<div>
    <h2>Change Stock/Crypto</h2>
    <input type="text" value={symbol} onChange={(e) => setSymbol(e.target.value)} />
    <button onClick={handleSubmit}>Submit</button>
<button onClick={onClose}>Close</button>
        </div>
        );
        };

        export default ChangeStockModal;
