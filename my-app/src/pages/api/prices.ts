import type { NextApiRequest, NextApiResponse } from 'next';
import axios from 'axios';

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
    const { symbol } = req.query;

    try {
        // Replace this with the actual URL of your Spring Boot backend
        const response = await axios.get(`http://localhost:8080/api/prices?symbol=${symbol}`);
        res.status(200).json(response.data);
    } catch (error) {
        res.status(500).json({ message: 'Error fetching prices' });
    }
};

export default handler;
