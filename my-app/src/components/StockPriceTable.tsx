// src/components/StockPriceTable.tsx
        import React, { useEffect } from 'react';
        import { useDispatch, useSelector } from 'react-redux';
        import axios from 'axios';
        import { setPrices } from '../pricesSlice';

        const StockPriceTable: React.FC<{ symbol: string }> = ({ symbol }) => {
        const dispatch = useDispatch();
        const prices = useSelector((state: any) => state.prices.prices);

        const fetchPrices = async () => {
        const response = await axios.get(`/api/prices?symbol=${symbol}`);
        dispatch(setPrices(response.data));
        };

        useEffect(() => {
        fetchPrices();
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
    {prices.map((price: any) => (
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
