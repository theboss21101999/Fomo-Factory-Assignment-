import React, { useState } from 'react';
        import StockPriceTable from '../components/StockPriceTable';
        import SymbolModal from '../components/SymbolModal';

        const HomePage: React.FC = () => {
        const [symbol, setSymbol] = useState('bitcoin');
        const [showModal, setShowModal] = useState(false);

        const handleSymbolChange = (newSymbol: string) => {
        setSymbol(newSymbol);
        setShowModal(false);
        };

        return (
<div>
    <h1>Real-Time Price Data</h1>
    <button onClick={() => setShowModal(true)}>Change Symbol</button>
        {showModal && <SymbolModal onClose={() => setShowModal(false)} />}
<StockPriceTable symbol={symbol} />
        </div>
        );
        };

        export default HomePage;
