import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'
// This is Vite config
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
})
