import React, { useEffect } from 'react';
        import { useDispatch, useSelector } from 'react-redux';
        import axios from 'axios';
        import { setPrices } from '../pricesSlice';
        import { RootState } from '../store';

        interface Price {
        id: string;
        price: number;
        timestamp: string;
        }

        const StockPriceTable: React.FC<{ symbol: string }> = ({ symbol }) => {
        const dispatch = useDispatch();
        const prices = useSelector((state: RootState) => state.prices.prices);

        const fetchPrices = async () => {
        const response = await axios.get(`/api/prices?symbol=${symbol}`);
        dispatch(setPrices(response.data));
        };

        useEffect(() => {
        fetchPrices();
        const interval = setInterval(fetchPrices, 5000);
        return () => clearInterval(interval);
        }, [symbol]);

        return (
<table>
<thead>
    <tr>
        <th>Price</th>
        <th>Timestamp</th>
    </tr>
</thead>
<tbody>
    {prices.slice(0, 20).map((price: Price) => (
    <tr key={price.id}>
    <td>{price.price}</td>
    <td>{new Date(price.timestamp).toLocaleString()}</td>
</tr>
))}
</tbody>
        </table>
        );
        };

        export default StockPriceTable;
