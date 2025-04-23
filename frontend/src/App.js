
import React, { useEffect, useState } from 'react';
import { BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid, ResponsiveContainer, Legend } from 'recharts';
import ActionsByUser from './components/ActionsByUser';
import TotalActions from './components/TotalActions';
import ActionsByDate from './components/ActionsByDate';
import ActionButton from './components/ActionButton';


function App() {
  const [actionCounts, setActionCounts] = useState({});
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  const refreshState = () => {
    fetch('http://localhost:8080/track/stats/by-action')
      .then(response => response.json())
      .then(data => setActionCounts(data))
      .catch(error => console.error('Error fetching action stats:', error));

  };

  useEffect(() => {
    refreshState();
  }, []);

  const handleRefresh = () => {
    setRefreshTrigger(prev => prev + 1);
    refreshState();
  };

  // ממירים את האובייקט למערך שמתאים לגרף
  const chartData = Object.entries(actionCounts).map(([action, count]) => ({
    action,
    count
  }));

  const handleDeleteAll = () => {
    if (window.confirm('Are you sure you want to delete all actions?')) {
      fetch('http://localhost:8080/track/delete', { method: 'DELETE' })
        .then(response => {
          if (!response.ok) throw new Error('Failed to delete actions');
          alert('All actions deleted successfully');
          handleRefresh(); // ⬅️ נרענן הכל במקום reload
        })
        .catch(error => {
          console.error('Error deleting actions:', error);
          alert('Failed to delete actions');
        });
    }
  };


  return (
    <div style={{ textAlign: 'center', padding: '40px', fontFamily: 'Arial' }}>
      <h1>User Analytics Dashboard</h1>

      <TotalActions refreshTrigger={refreshTrigger} />
      <ActionsByUser refreshTrigger={refreshTrigger} />
      <ActionsByDate refreshTrigger={refreshTrigger} />

      <h2>Trigger Action</h2>

      <ActionButton actionName="login_button" label="Login" userId="user_login" onActionSent={handleRefresh} />
      <ActionButton actionName="signup_button" label="Sign Up" userId="user_signup" onActionSent={handleRefresh} />
      <ActionButton actionName="purchase_button" label="Purchase" userId="user_purchase" onActionSent={handleRefresh} />

      <button
        onClick={handleDeleteAll}
        style={{
          marginLeft: '20px',
          padding: '10px 20px',
          backgroundColor: '#ff4d4f',
          color: 'white',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer'
        }}
      >
        Delete All Actions
      </button>



      <div style={{ width: '80%', height: 400, margin: '40px auto' }}>
        <ResponsiveContainer width="100%" height="100%">
          <BarChart
            data={chartData}
            margin={{ top: 20, right: 30, left: 20, bottom: 20 }}
            barCategoryGap="20%"
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="action" />
            <YAxis />
            <Tooltip 
              conttentStyle={{ backgroundColor: '#fff', border: '1px solid #ccc' }}
              labelStyle={{ fontWeight: 'bold' }}
            />
            <Legend verticalAlign='top' height={36} /> 
            <Bar
              dataKey="count"
              name="Number of Clicks"
              fill="#82ca9d"
              radius={[10, 10, 0, 0]}
              animationDuration={800}
            />
            <Bar
              dataKey="count"
              name="Number of Clicks"
              fill="#82ca9d"
              radius={[10, 10, 0, 0]}
              animationDuration={800}
            />
          </BarChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}
export default App;
