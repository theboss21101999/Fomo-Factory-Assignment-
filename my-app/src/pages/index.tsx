import React, { useState } from 'react';
        import StockPriceTable from '../components/StockPriceTable';
        import ChangeStockModal from '../components/ChangeStockModal';

        const Home: React.FC = () => {
        const [symbol, setSymbol] = useState('GOOG');
        const [isModalOpen, setIsModalOpen] = useState(false);

        return (
<div>
    <button onClick={() => setIsModalOpen(true)}>Change Stock</button>
        {isModalOpen && (
<ChangeStockModal
onChange={(newSymbol) => setSymbol(newSymbol)}
        onClose={() => setIsModalOpen(false)}
        />
        )}
<StockPriceTable symbol={symbol} />
        </div>
        );
        };

        export default Home;
