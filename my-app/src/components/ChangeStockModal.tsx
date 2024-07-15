import React, { useState } from 'react';

        interface ChangeStockModalProps {
        onChange: (newSymbol: string) => void;
        onClose: () => void;
        }

        const ChangeStockModal: React.FC<ChangeStockModalProps> = ({ onChange, onClose }) => {
    const [symbol, setSymbol] = useState('');

    const handleSubmit = () => {
    onChange(symbol);
    onClose();
    };

    return (
    <div>
        <h2>Change Stock Symbol</h2>
        <input
                type="text"
                value={symbol}
        onChange={(e) => setSymbol(e.target.value)}
        />
        <button onClick={handleSubmit}>Submit</button>
    <button onClick={onClose}>Cancel</button>
        </div>
        );
        };

        export default ChangeStockModal;
