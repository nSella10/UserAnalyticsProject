
import React, { useEffect, useState } from 'react';
import { BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid, ResponsiveContainer } from 'recharts';
import ActionsByUser from './components/ActionsByUser';
import TotalActions from './components/TotalActions';
import ActionsByDate from './components/ActionsByDate';


function App() {
  const [actionCounts, setActionCounts] = useState({});

  useEffect(() => {
    fetch('http://localhost:8080/track/stats/by-action')
      .then(response => response.json())
      .then(data => setActionCounts(data))
      .catch(error => console.error('Error fetching action stats:', error));
  }, []);

  // ממירים את האובייקט למערך שמתאים לגרף
  const chartData = Object.entries(actionCounts).map(([action, count]) => ({
    action,
    count
  }));

  return (
    <div style={{ textAlign: 'center', padding: '40px', fontFamily: 'Arial' }}>
      <h1>User Analytics Dashboard</h1>

     <TotalActions />
     <ActionsByUser />
     <ActionsByDate />


      <h2>Actions Breakdown (Graph)</h2>
      <div style={{ width: '80%', height: 300, margin: '0 auto' }}>
        <ResponsiveContainer width="100%" height="100%">
          <BarChart data={chartData}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="action" />
            <YAxis />
            <Tooltip />
            <Bar dataKey="count" fill="#8884d8" />
          </BarChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}

export default App;
