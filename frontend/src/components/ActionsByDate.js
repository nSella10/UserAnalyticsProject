import React, { useState, useEffect } from 'react';
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer
} from 'recharts';


function ActionsByDate() {
  const [dateCounts, setDateCounts] = useState({});

  useEffect(() => {
    fetch('http://localhost:8080/track/stats/by-date')
      .then(response => response.json())
      .then(data => setDateCounts(data))
      .catch(error => console.error('Error fetching date stats:', error));
  }, []);

  const chartData = Object.entries(dateCounts).map(([date, count]) => ({
    date,
    count
  }));

const charData = Object.entries(dateCounts).map(([date, count]) => ({date, count}));


  return (
    <div style={{ marginTop: '60px' }}>
      <h2>Actions Over Time</h2>
      <div style={{ width: '80%', height: 300, margin: '0 auto' }}>
        <ResponsiveContainer width="100%" height="100%">
          <LineChart data={charData}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="date" />
            <YAxis />
            <Tooltip />
            <Line type="monotone" dataKey="count" stroke="#82ca9d" />
          </LineChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}

export default ActionsByDate;