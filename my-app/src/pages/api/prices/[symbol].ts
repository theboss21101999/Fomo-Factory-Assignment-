import { NextApiRequest, NextApiResponse } from 'next';
import axios from 'axios';

export default async (req: NextApiRequest, res: NextApiResponse) => {
    const { symbol } = req.query;

    try {
        const response = await axios.get(`http://localhost:8080/api/prices/${symbol}`);
        res.status(200).json(response.data);
    } catch (error) {
        res.status(500).json({ message: 'Error fetching data' });
    }
};
